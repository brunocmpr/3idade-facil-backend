package com.campera.app3idadefacil.service;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.Authority;
import com.campera.app3idadefacil.model.datatransfer.mapper.AppUserMapper;
import com.campera.app3idadefacil.model.datatransfer.dto.AppUserDto;
import com.campera.app3idadefacil.model.datatransfer.form.AppUserForm;
import com.campera.app3idadefacil.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    @Autowired
    AppUserRepository userRepository;

    public AppUserDto register(AppUserForm appUserForm){
        AppUser appUser = AppUserMapper.convertFromForm(appUserForm);
        appUser.addAuthority(Authority.ACCOUNT_ADMIN);
        appUser.addAuthority(Authority.CAREGIVER);
        appUser = userRepository.save(appUser);
        return AppUserMapper.convertToDto(appUser);
    }
}