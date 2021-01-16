/**
 * The package provides utilities for converting <br>
 * <ul>
 *     <li>webflux reactive response</li>
 *     <li>webmvc response</li>
 * </ul>
 *  into result or throwing runtime exception.
 */
package com.spring.libs.flux.exception.utils;

import org.springframework.http.HttpStatus;

class HttpErrorChecker {
    static boolean isCustomError(HttpStatus status) {
        return status.equals(HttpStatus.BAD_REQUEST) ||
                status.equals(HttpStatus.UNAUTHORIZED) ||
                status.equals(HttpStatus.FORBIDDEN) ||
                status.equals(HttpStatus.NOT_FOUND) ||
                status.equals(HttpStatus.CONFLICT) ||
                status.equals(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}