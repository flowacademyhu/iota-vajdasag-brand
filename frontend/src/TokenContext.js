import React, { useState } from 'react';


export const TokenContext = React.createContext()


export const TokenProvider = ({ children }) => {
    const [token, setToken] = useState(sessionStorage.getItem("token"));

    return (
        <TokenContext.Provider value={{token, setToken}}>
            {children}
        </TokenContext.Provider>
    );
}


