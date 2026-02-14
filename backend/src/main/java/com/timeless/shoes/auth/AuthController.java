@PostMapping("/login")
public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {
    var user = authService.authenticate(request.getUsername(), request.getPassword());
    LoginResponse response = new LoginResponse(user.getUsername(), user.getRole());
    return new ApiResponse<>(true, "Logged in", response);
}
