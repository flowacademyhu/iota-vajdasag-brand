import { useState } from "react";

export const TokenStateHandler = () => {
    const [token, setTokenInState] = useState();
    return {
        token,
        setTokenInState
    }
}

export const getTokenFromStorage = () => {
    return sessionStorage.getItem("token");
}

export const writeToken = (token) => (
    sessionStorage.setItem("token", token)
)

export const deleteToken = () => {
    sessionStorage.removeItem("token")
}