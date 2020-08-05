package io.App.RoleCatalogService.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.App.RoleCatalogService.dto.Pair;
import io.App.RoleCatalogService.dto.RoleDTO;
import io.App.RoleCatalogService.dto.RoleListWrapper;
import io.App.RoleCatalogService.exceptions.InternalAppException;
import io.App.RoleCatalogService.exceptions.RoleAlreadyExistsException;
import io.App.RoleCatalogService.exceptions.RoleDoesNotExistException;
import io.App.RoleCatalogService.roleComponent.Role;
import io.App.RoleCatalogService.roleComponent.RoleCatalog;

@RestController
@EnableAutoConfiguration
@RequestMapping("/roleApi")
public class RoleCatalogController {

	@Autowired
	private RoleCatalog rC;

	private static final String INTERNAL_APP_ERROR_MESSAGE = "Internal Application Error";

	@GetMapping("/roleCatalogList")
	public ResponseEntity<Pair<String, RoleListWrapper>> list() {
		RoleListWrapper rLW = null;

		try {
			rLW = rC.getRoleList();
		} catch (InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		System.out.println("Successfull roles request");
		return new ResponseEntity<>(
				new Pair<>("Successfull roles request", rLW), HttpStatus.OK);
	}

	@PostMapping("addRole/role")
	public ResponseEntity<String> addRole(@RequestBody String roleJSON) {
		ObjectMapper objectMapper = new ObjectMapper();
		RoleDTO rDTO = null;

		try {
			rDTO = objectMapper.readValue(roleJSON, RoleDTO.class);
			rC.addRole(new Role(Integer.parseInt(rDTO.getId()), rDTO.getName(),
					Integer.parseInt(rDTO.getCommunityID()),
					Integer.parseInt(rDTO.getAuthLevel())));
		} catch (JsonParseException | JsonMappingException
				| NumberFormatException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (RoleAlreadyExistsException | InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		System.out.println("Role added succesfully");
		return new ResponseEntity<>("Role added succesfully", HttpStatus.OK);

	}

	@PostMapping("removeRole/role")
	public ResponseEntity<String> removeRole(@RequestBody String roleJSON)
			throws RoleDoesNotExistException {
		ObjectMapper objectMapper = new ObjectMapper();
		RoleDTO rDTO = null;

		try {
			rDTO = objectMapper.readValue(roleJSON, RoleDTO.class);

			rC.removeRole(new Role(Integer.parseInt(rDTO.getId()),
					rDTO.getName(), Integer.parseInt(rDTO.getCommunityID()),
					Integer.parseInt(rDTO.getAuthLevel())));
		} catch (JsonParseException | JsonMappingException
				| NumberFormatException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		System.out.println("Role deleted succesfully");
		return new ResponseEntity<>("Role deleted succesfully", HttpStatus.OK);
	}
}
