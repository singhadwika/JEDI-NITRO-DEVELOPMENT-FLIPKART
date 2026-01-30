package com.flipfit.client;

import com.flipfit.bean.User;
import com.flipfit.bean.Admin;
import com.flipfit.bean.GymOwner;
import com.flipfit.bean.GymCustomer;
import com.flipfit.business.UserService;
import com.flipfit.business.UserServiceImpl;
import com.flipfit.dto.LoginRequest;
import com.flipfit.dto.RegisterRequest;
import com.flipfit.dto.ApiResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * The Class UserController.
 * REST Controller for user authentication and management operations.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    /** The user service. */
    private final UserService userService;

    /**
     * Instantiates a new user controller.
     */
    public UserController() {
        this.userService = new UserServiceImpl();
    }

    /**
     * Authenticates a user with provided credentials.
     *
     * @param loginRequest the login request containing email and password
     * @return the response with user details if authentication is successful
     */
    @POST
    @Path("/login")
    public Response login(LoginRequest loginRequest) {
        try {
            User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
            if (user != null) {
                return Response.ok(new ApiResponse<>(true, "Login successful", user)).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(new ApiResponse<>(false, "Invalid credentials", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Login failed: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Registers a new user in the system.
     *
     * @param registerRequest the registration request
     * @return the response indicating registration status
     */
    @POST
    @Path("/register")
    public Response register(RegisterRequest registerRequest) {
        try {
            User user = createUserByRole(registerRequest);
            if (user == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Invalid role specified", null))
                        .build();
            }

            boolean success = userService.register(user);
            if (success) {
                return Response.status(Response.Status.CREATED)
                        .entity(new ApiResponse<>(true, "Registration successful", user))
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Registration failed", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Registration failed: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Creates a user instance based on role.
     *
     * @param request the registration request
     * @return the user instance
     */
    private User createUserByRole(RegisterRequest request) {
        User user;
        switch (request.getRole().toUpperCase()) {
            case "ADMIN":
                user = new Admin();
                user.setRole("ADMIN");
                break;
            case "OWNER":
            case "GYM_OWNER":
                user = new GymOwner();
                user.setRole("GYM_OWNER");
                break;
            case "CUSTOMER":
            case "GYM_CUSTOMER":
                user = new GymCustomer();
                user.setRole("GYM_CUSTOMER");
                break;
            default:
                return null;
        }
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }

    /**
     * Retrieves user details by user ID.
     *
     * @param userId the user id
     * @return the response with user details
     */
    @GET
    @Path("/{userId}")
    public Response getUserById(@PathParam("userId") int userId) {
        try {
            User user = userService.getUserDetails(userId);
            if (user != null) {
                return Response.ok(new ApiResponse<>(true, "User found", user)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ApiResponse<>(false, "User not found", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving user: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Retrieves all users in the system.
     *
     * @return the response with list of all users
     */
    @GET
    public Response getAllUsers() {
        try {
            return Response.ok(new ApiResponse<>(true, "Users retrieved successfully", userService.getAllUsers())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving users: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Updates user profile.
     *
     * @param userId the user id
     * @param registerRequest the update request
     * @return the response indicating update status
     */
    @PUT
    @Path("/{userId}")
    public Response updateProfile(@PathParam("userId") int userId, RegisterRequest registerRequest) {
        try {
            boolean success = userService.updateProfile(userId, registerRequest.getName(), 
                    registerRequest.getEmail(), registerRequest.getPassword());
            if (success) {
                return Response.ok(new ApiResponse<>(true, "Profile updated successfully", null)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Profile update failed", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error updating profile: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Logs out a user from the system.
     *
     * @param userId the user id
     * @return the response indicating logout status
     */
    @POST
    @Path("/{userId}/logout")
    public Response logout(@PathParam("userId") int userId) {
        try {
            boolean success = userService.logout(userId);
            return Response.ok(new ApiResponse<>(success, success ? "Logout successful" : "Logout failed", null)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error during logout: " + e.getMessage(), null))
                    .build();
        }
    }
}
