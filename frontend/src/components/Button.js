import React from "react";


export default ({type,children})=>(
    <button className="btn btn-primary btn-block col-12" type={type}>{children}</button>
)