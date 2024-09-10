package io.demoprojects.dak.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.demoprojects.dak.dto.MainRequestDto;
import io.demoprojects.dak.model.MainRequisition;
import io.demoprojects.dak.model.MainRequisitionStatusHistory;
import io.demoprojects.dak.model.Requisition;
import io.demoprojects.dak.model.Upload;
import io.demoprojects.dak.payload.ApiResponse;
import io.demoprojects.dak.service.IRequisitionService;
import io.demoprojects.dak.util.RequisitionDetailDto;
import io.demoprojects.dak.util.RequisitionUpdateDto;

@RestController
@RequestMapping("/api")
public class RequisitionController {

//	@Autowired
//    private RequisitionService requisitionService;

	 @Autowired
	 private IRequisitionService requisitionService;
	 
//	 private final AuthenticationManager authenticationManager;
//
//	    @Autowired
//	    public RequisitionController(AuthenticationManager authenticationManager) {
//	        this.authenticationManager = authenticationManager;
//	    }
	    
//	 @GetMapping("/")
//	 public String index() {
//		 return "Welcome to Home Page";
//	 }
	 

//	 @RequestMapping(value = { "/", "/{x:[\\w\\-]+}", "/**/{x:[\\w\\-]+}", "/{x:[\\w\\-]+}/**/{y:[\\w\\-]+}" })
//	 public String index() {
//	     return "index"; // This refers to index.html in /static or /templates
//	  }
	 
//	 @RequestMapping(value = {"/"})
//	 public String index() {
//	     return "index";
//	 }

	    @RequestMapping(value = {"/", "/{x:[\\w\\-]+}", "/{x:^(?!api$).*$}**/{y:[\\w\\-]+}"})
	    public String index() {
	        return "index";
	    }
	 
//	    @PostMapping("/mainrequest")
//	    public ResponseEntity<MainRequisition> createRequisition(@RequestBody MainRequisition requisition) {
//	        MainRequisition createdRequisition = requisitionService.createMainRequisition(requisition);
//	        return ResponseEntity.ok(createdRequisition);
//	    }
	    
	    @PostMapping("/mainrequest")
	    public ResponseEntity<ApiResponse> createMainRequisition(@RequestBody MainRequisition requisition) {
	        try {
	        	MainRequisition createdRequisition = requisitionService.createMainRequisition(requisition);
	            ApiResponse response = new ApiResponse(
	                    HttpStatus.OK.value(),
	                    "Requisition created successfully",
	                    createdRequisition  // can replace with any additional data if needed
	            );
	            return ResponseEntity.ok(response);
	        } catch (Exception e) {
	            ApiResponse response = new ApiResponse(
	                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
	                    "Failed to create requisition: " + e.getMessage(),
	                    null
	            );
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	        }
	    }
	    
	    @GetMapping("/request-details")
	    public ResponseEntity<List<RequisitionDetailDto>> getAllRequisitionsByRequestdate() {
	        List<RequisitionDetailDto> requisitions = requisitionService.getAllRequisitions();
	        return ResponseEntity.ok(requisitions);
	    }
	    
	    @GetMapping("/getrequests")
	    public ResponseEntity<List<MainRequisition>> getAllRequests() {
	        List<MainRequisition> requisitions = requisitionService.getAllRequests();

	        // Populate each requisition with its status histories
	        List<MainRequisition> requisitionsWithStatus = requisitions.stream().map(requisition -> {
	            List<MainRequisitionStatusHistory> statusHistories = requisitionService.getStatusHistoriesByRequisitionId(requisition.getRequestid());
	            requisition.setStatusHistories(statusHistories);
	            return requisition;
	        }).collect(Collectors.toList());

	        return ResponseEntity.ok(requisitionsWithStatus);
	    }

	    @GetMapping("/getrequestsbystatus")
	    public ResponseEntity<List<MainRequisition>> getAllRequestsByLatestStatus() {
	        // Call the service method to get requisitions with the latest status
	        List<MainRequisition> requisitionsWithLatestStatus = requisitionService.getAllRequests();
	        
	        // Return the response entity with the list of requisitions
	        return ResponseEntity.ok(requisitionsWithLatestStatus);
	    }
	    
	    @GetMapping("/allWithLatestStatus")
	    public ResponseEntity<List<MainRequestDto>> getAllRequisitionsWithLatestStatus() {
	        List<MainRequestDto> requisitions = requisitionService.getAllRequisitionsWithLatestStatus();
	        return ResponseEntity.ok(requisitions);
	    }
	
	    @PutMapping("/requisition/{id}")
	    public ResponseEntity<MainRequisition> updateRequisition(@PathVariable Long id, @RequestBody MainRequestDto requisitionDTO) {
	        MainRequisition updatedRequisition = requisitionService.updateRequisition(id, requisitionDTO);
	        return ResponseEntity.ok(updatedRequisition);
	    }
	    
	    @DeleteMapping("/requisition/{id}")
	    public ResponseEntity<Void> deleteRequisition(@PathVariable Long id) {
	        requisitionService.deleteRequisition(id);
	        return ResponseEntity.noContent().build(); // Return 204 No Content status
	    }
	    
	    @PostMapping("/upload")
	    public ResponseEntity<String> uploadFile(@RequestParam("name") String name,
	                                             @RequestParam("file") MultipartFile file) {
	        try {
	        	requisitionService.saveUpload(name, file);
	            return ResponseEntity.ok("File uploaded successfully");
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body("Failed to upload file: " + e.getMessage());
	        }
	    }
	    
	    @GetMapping("/file/{id}")
	    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
	        Upload upload = requisitionService.getUpload(id);
	        if (upload != null) {
	            return ResponseEntity.ok()
	                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + upload.getFileName() + "\"")
	                    .body(upload.getFileData());
	        }
	        return ResponseEntity.notFound().build();
	    }

	    @PostMapping("/mainrequestwithfile")
	    public ResponseEntity<MainRequisition> addRequisition(
	            @RequestParam("requestdate") String requestdate,
	            @RequestParam("receivedfrom") String receivedfrom,
	            @RequestParam("directorate") String directorate,
	            @RequestParam("subject") String subject,
	            @RequestParam("remarks") String remarks,
	            @RequestParam("dakfile") MultipartFile dakfile) {
	        try {
	            MainRequisition requisition = new MainRequisition();
	            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	            Date date = formatter.parse(requestdate);
	            requisition.setRequestdate(date);
	            requisition.setReceivedfrom(receivedfrom);
	            requisition.setDirectorate(directorate);
	            requisition.setSubject(subject);
	            requisition.setRemarks(remarks);

	            MainRequisition savedRequisition = requisitionService.saveRequisition(requisition, dakfile);
	            return ResponseEntity.ok(savedRequisition);
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body(null);
	        }
	    }
//	 @PostMapping("/postreq")
//	 public ResponseEntity<Requisition> createRequisition(@RequestBody Requisition requisition) {
//	        Requisition savedRequisition = requisitionService.saveRequisition(requisition);
//	        return ResponseEntity.ok(savedRequisition);
//	    }
	 
	    @PostMapping("/add-requisition")
	    public ResponseEntity<ApiResponse> createRequisition(@RequestBody RequisitionDetailDto createDto) {
	        try {
	            requisitionService.createRequisition(createDto);
	            ApiResponse response = new ApiResponse(
	                    HttpStatus.OK.value(),
	                    "Requisition created successfully",
	                    null  // You can replace null with any additional data if needed
	            );
	            return ResponseEntity.ok(response);
	        } catch (Exception e) {
	            ApiResponse response = new ApiResponse(
	                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
	                    "Failed to create requisition: " + e.getMessage(),
	                    null
	            );
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	        }
	    }

	 
	 @GetMapping("/info")
	    public ResponseEntity<String> getCurrentUser() {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if (authentication != null && authentication.isAuthenticated()) {
	            Object principal = authentication.getPrincipal();
	            String username;
	            if (principal instanceof UserDetails) {
	                username = ((UserDetails) principal).getUsername();
	            } else {
	                username = principal.toString();
	            }
	            return ResponseEntity.ok("Hello, " + username);
	        }
	        return ResponseEntity.status(401).body("User is not authenticated");
	    }
    
	 @PatchMapping("/{id}")
	    public ResponseEntity<?> updateRequisition(@PathVariable Long id, @RequestBody RequisitionUpdateDto updateDto) {
	        try {
	            Requisition updatedRequisition = requisitionService.updateRequisition(id, updateDto);
	            return ResponseEntity.ok(updatedRequisition);
	        } catch (ResourceNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        }
	    }
	 
	 @GetMapping("/requests")
	    public ResponseEntity<List<RequisitionDetailDto>> getAllRequisitions() {
		 System.out.println(requisitionService.getAllRequisitions().toString());
	        return ResponseEntity.ok(requisitionService.getAllRequisitions());
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<RequisitionDetailDto> getRequisitionDetails(@PathVariable Long id) {
	        return ResponseEntity.ok(requisitionService.getRequisitionDetails(id));
	    }
//	 @DeleteMapping("/{id}")
//	    public ResponseEntity<?> deleteRequisition(@PathVariable Long id) {
//	        try {
//	            requisitionService.deleteRequisition(id);
//	            return ResponseEntity.ok("Requisition deleted successfully");
//	        } catch (ResourceNotFoundException e) {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//	        }
//	    }
//    @GetMapping("/details/{demandNo}")
//    public List<RequisitionDetailDto> getRequisitionItemDetails(@PathVariable Long demandNo) {
//        return requisitionService.getRequisitionItemDetails(demandNo);
//    }
	    
//	    @PostMapping("/signin")
//	    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequestDto loginRequest, HttpServletRequest request) {
//	        Map<String, String> response = new HashMap<>();
//	        try {
//	            UsernamePasswordAuthenticationToken authToken =
//	                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
//
//	            // Authenticate the user
//	            Authentication authentication = authenticationManager.authenticate(authToken);
//	            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//	            // Create a session and store the authentication object
//	            HttpSession session = request.getSession(true);
//	            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
//	            System.out.println("SecurityContextHolder.getContext() :: "+ SecurityContextHolder.getContext());
//	            // Prepare JSON response
//	            response.put("message", "Login successful, session created.");
//	            return ResponseEntity.ok(response);
//	        } catch (Exception e) {
//	            response.put("error", "Invalid username or password.");
//	            return ResponseEntity.status(401).body(response);
//	        }
//	    }
	   
	}
	    
	    

