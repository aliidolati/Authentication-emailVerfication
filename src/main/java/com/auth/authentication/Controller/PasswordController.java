package com.example.authentication.Controller;

import com.example.authentication.Model.dto.PasswordRequest;
import com.example.authentication.Model.dto.ResetPasswordRequest;
import com.example.authentication.exception.ServiceException;
import com.example.authentication.service.ResetPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password")
@RequiredArgsConstructor
public class PasswordController {
    private final ResetPasswordService resetPasswordService ;
    @PostMapping("/change-password")
    public String resetPassword(@RequestBody ResetPasswordRequest request ) {
        return resetPasswordService.generateResetPasswordToken(request.getEmail()) ;
    }
    @PostMapping("/reset-password/confirm")
    public String changePassword(@RequestParam("token") String token,
                                 @RequestBody PasswordRequest request) throws ServiceException {
        return resetPasswordService.resetPassword(token , request.getPassword()) ;
    }
}
