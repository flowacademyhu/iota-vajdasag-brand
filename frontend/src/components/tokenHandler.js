import { useState } from "react";

export const useTokenStateHandler = () => {
    const [token, setTokenInState] = useState();

    const getTokenFromStorage = () => {
        return sessionStorage.getItem("token");
    }
    
    const writeToken = (token) => {
        sessionStorage.setItem("token", token)
        setTokenInState(token)
}
    
    const deleteToken = () => {
        sessionStorage.removeItem("token")
        setTokenInState(null)
    }

    return {
        token,
        getTokenFromStorage,
        writeToken,
        deleteToken        
    }
}

