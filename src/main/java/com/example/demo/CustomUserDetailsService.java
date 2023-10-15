// package com.example.demo;

// public class CustomUserDetailsService implements UserDetailsService{
    
//     private final UserRepository userRepository;

//     public CustomUserDetailsService(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundExcetion {
//         User user = userRepository.findByUsername(username);

//         if (user == null) {
//             throw new UsernameNotFoundException("User not found");
//         }

//         return new CustomUserDetails(user);
//     }

// }
