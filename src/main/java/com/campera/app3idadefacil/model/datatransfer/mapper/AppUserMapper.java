package com.campera.app3idadefacil.model.datatransfer.mapper;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.datatransfer.dto.AppUserDto;
import com.campera.app3idadefacil.model.datatransfer.form.AppUserForm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class AppUserMapper {
    public static AppUserDto convertToDto(AppUser appUser){
        return new AppUserDto(appUser.getId(), appUser.getFirstName(), appUser.getLastName(), appUser.getCountryCode(),
                appUser.getAreaCode(), appUser.getPhoneNumber(), appUser.getEmail());
    }

    public static AppUser convertFromForm(AppUserForm form){
        String hashedPassword = new BCryptPasswordEncoder().encode(form.getRawPassword());
        return new AppUser(form.getFirstName(), form.getLastName(), form.getCountryCode(), form.getAreaCode(),
                form.getPhoneNumber(), form.getEmail(), hashedPassword);
    }
}
