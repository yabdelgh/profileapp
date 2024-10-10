package  ma.zyn.app.ws.facade.admin.profile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.profile.Profile;
import ma.zyn.app.dao.criteria.core.profile.ProfileCriteria;
import ma.zyn.app.service.facade.admin.profile.ProfileAdminService;
import ma.zyn.app.ws.converter.profile.ProfileConverter;
import ma.zyn.app.ws.dto.profile.ProfileDto;
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
@RequestMapping("/api/admin/profile/")
public class ProfileRestAdmin {




    @Operation(summary = "Finds a list of all profiles")
    @GetMapping("")
    public ResponseEntity<List<ProfileDto>> findAll() throws Exception {
        ResponseEntity<List<ProfileDto>> res = null;
        List<Profile> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<ProfileDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a profile by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProfileDto> findById(@PathVariable Long id) {
        Profile t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ProfileDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  profile")
    @PostMapping("")
    public ResponseEntity<ProfileDto> save(@RequestBody ProfileDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Profile myT = converter.toItem(dto);
            Profile t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ProfileDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  profile")
    @PutMapping("")
    public ResponseEntity<ProfileDto> update(@RequestBody ProfileDto dto) throws Exception {
        ResponseEntity<ProfileDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Profile t = service.findById(dto.getId());
            converter.copy(dto,t);
            Profile updated = service.update(t);
            ProfileDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of profile")
    @PostMapping("multiple")
    public ResponseEntity<List<ProfileDto>> delete(@RequestBody List<ProfileDto> dtos) throws Exception {
        ResponseEntity<List<ProfileDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Profile> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified profile")
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


    @Operation(summary = "Finds a profile and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProfileDto> findWithAssociatedLists(@PathVariable Long id) {
        Profile loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ProfileDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds profiles by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ProfileDto>> findByCriteria(@RequestBody ProfileCriteria criteria) throws Exception {
        ResponseEntity<List<ProfileDto>> res = null;
        List<Profile> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ProfileDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated profiles by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ProfileCriteria criteria) throws Exception {
        List<Profile> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ProfileDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets profile data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ProfileCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ProfileDto> findDtos(List<Profile> list){
        converter.initObject(true);
        List<ProfileDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProfileDto> getDtoResponseEntity(ProfileDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public ProfileRestAdmin(ProfileAdminService service, ProfileConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ProfileAdminService service;
    private final ProfileConverter converter;





}
