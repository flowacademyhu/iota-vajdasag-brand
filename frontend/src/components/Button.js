import React from "react";


const Button = ({ type, children, className }) => (
    <button className={className} type={type}>{children}</button>
)

export default Button