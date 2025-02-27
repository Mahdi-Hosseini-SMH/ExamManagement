//package ir.maktabsharif.exammanagement.service.impl;
//
//import ir.maktabsharif.exammanagement.model.entity.Teacher;
//import ir.maktabsharif.exammanagement.repository.TeacherRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private TeacherRepository teacherRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Teacher teacher = teacherRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        return new CustomUserDetails(teacher);
//    }
//}