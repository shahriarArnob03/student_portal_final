package com.studentPortalRegistration;

/**
 * Represents a user (student) in the portal.
 * Supports both student-view (authorId, name, profilePicture)
 * and admin-view (authorId, name, profilePicture, email).
 */
public class User {
    private final String authorId;
    private final String name;
    private final String profilePicture;
    private final String email;  // may be null for student-dashboard use

    /**
     * 3-arg constructor for student dashboard (no email needed)
     */
    public User(String authorId, String name, String profilePicture) {
        this(authorId, name, profilePicture, null);
    }

    /**
     * 4-arg constructor for admin dashboard (includes email)
     */
    public User(String authorId, String name, String profilePicture, String email) {
        this.authorId = authorId;
        this.name = name;
        this.profilePicture = profilePicture;
        this.email = email;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getName() {
        return name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    /**
     * Returns the email if set (admin view), or null if not provided.
     */
    public String getEmail() {
        return email;
    }
}
