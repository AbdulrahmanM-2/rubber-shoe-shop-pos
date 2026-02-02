BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
user.setPasswordHash(encoder.encode("1234"));
