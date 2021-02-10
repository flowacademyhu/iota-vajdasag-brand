import React from "react";


const Button = ({ type, children, design }) => (
    <button className={design} type={type}>{children}</button>
)

export default Button