package  ma.zyn.app.ws.facade.admin.student;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.student.Student;
import ma.zyn.app.dao.criteria.core.student.StudentCriteria;
import ma.zyn.app.service.facade.admin.student.StudentAdminService;
import ma.zyn.app.ws.converter.student.StudentConverter;
import ma.zyn.app.ws.dto.student.StudentDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import ma.zyn.app.zynerator.security.bean.User;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/student/")
public class StudentRestAdmin {




    @Operation(summary = "Finds a list of all students")
    @GetMapping("")
    public ResponseEntity<List<StudentDto>> findAll() throws Exception {
        ResponseEntity<List<StudentDto>> res = null;
        List<Student> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<StudentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all students")
    @GetMapping("optimized")
    public ResponseEntity<List<StudentDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<StudentDto>> res = null;
        List<Student> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<StudentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a student by id")
    @GetMapping("id/{id}")
    public ResponseEntity<StudentDto> findById(@PathVariable Long id) {
        Student t = service.findById(id);
        if (t != null) {
            StudentDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a student by email")
    @GetMapping("email/{email}")
    public ResponseEntity<StudentDto> findByEmail(@PathVariable String email) {
	    Student t = service.findByReferenceEntity(new Student(email));
        if (t != null) {
            StudentDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  student")
    @PostMapping("")
    public ResponseEntity<StudentDto> save(@RequestBody StudentDto dto) throws Exception {
        if(dto!=null){
            Student myT = converter.toItem(dto);
            Student t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                StudentDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  student")
    @PutMapping("")
    public ResponseEntity<StudentDto> update(@RequestBody StudentDto dto) throws Exception {
        ResponseEntity<StudentDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Student t = service.findById(dto.getId());
            converter.copy(dto,t);
            Student updated = service.update(t);
            StudentDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of student")
    @PostMapping("multiple")
    public ResponseEntity<List<StudentDto>> delete(@RequestBody List<StudentDto> dtos) throws Exception {
        ResponseEntity<List<StudentDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Student> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified student")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }


    @Operation(summary = "Finds a student and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<StudentDto> findWithAssociatedLists(@PathVariable Long id) {
        Student loaded =  service.findWithAssociatedLists(id);
        StudentDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds students by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<StudentDto>> findByCriteria(@RequestBody StudentCriteria criteria) throws Exception {
        ResponseEntity<List<StudentDto>> res = null;
        List<Student> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<StudentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated students by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody StudentCriteria criteria) throws Exception {
        List<Student> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<StudentDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets student data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody StudentCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<StudentDto> findDtos(List<Student> list){
        List<StudentDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<StudentDto> getDtoResponseEntity(StudentDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

   public StudentRestAdmin(StudentAdminService service, StudentConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final StudentAdminService service;
    private final StudentConverter converter;





}
