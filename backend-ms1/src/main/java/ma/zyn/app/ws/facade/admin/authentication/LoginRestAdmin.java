package  ma.zyn.app.ws.facade.admin.authentication;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.authentication.Login;
import ma.zyn.app.dao.criteria.core.authentication.LoginCriteria;
import ma.zyn.app.service.facade.admin.authentication.LoginAdminService;
import ma.zyn.app.ws.converter.authentication.LoginConverter;
import ma.zyn.app.ws.dto.authentication.LoginDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/login/")
public class LoginRestAdmin {




    @Operation(summary = "Finds a list of all logins")
    @GetMapping("")
    public ResponseEntity<List<LoginDto>> findAll() throws Exception {
        ResponseEntity<List<LoginDto>> res = null;
        List<Login> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<LoginDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all logins")
    @GetMapping("optimized")
    public ResponseEntity<List<LoginDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<LoginDto>> res = null;
        List<Login> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<LoginDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a login by id")
    @GetMapping("id/{id}")
    public ResponseEntity<LoginDto> findById(@PathVariable Long id) {
        Login t = service.findById(id);
        if (t != null) {
            LoginDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a login by email")
    @GetMapping("email/{email}")
    public ResponseEntity<LoginDto> findByEmail(@PathVariable String email) {
	    Login t = service.findByReferenceEntity(new Login(email));
        if (t != null) {
            LoginDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  login")
    @PostMapping("")
    public ResponseEntity<LoginDto> save(@RequestBody LoginDto dto) throws Exception {
        if(dto!=null){
            Login myT = converter.toItem(dto);
            Login t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                LoginDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  login")
    @PutMapping("")
    public ResponseEntity<LoginDto> update(@RequestBody LoginDto dto) throws Exception {
        ResponseEntity<LoginDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Login t = service.findById(dto.getId());
            converter.copy(dto,t);
            Login updated = service.update(t);
            LoginDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of login")
    @PostMapping("multiple")
    public ResponseEntity<List<LoginDto>> delete(@RequestBody List<LoginDto> dtos) throws Exception {
        ResponseEntity<List<LoginDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Login> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified login")
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


    @Operation(summary = "Finds a login and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<LoginDto> findWithAssociatedLists(@PathVariable Long id) {
        Login loaded =  service.findWithAssociatedLists(id);
        LoginDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds logins by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<LoginDto>> findByCriteria(@RequestBody LoginCriteria criteria) throws Exception {
        ResponseEntity<List<LoginDto>> res = null;
        List<Login> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<LoginDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated logins by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody LoginCriteria criteria) throws Exception {
        List<Login> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<LoginDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets login data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody LoginCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<LoginDto> findDtos(List<Login> list){
        List<LoginDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<LoginDto> getDtoResponseEntity(LoginDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public LoginRestAdmin(LoginAdminService service, LoginConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final LoginAdminService service;
    private final LoginConverter converter;





}
