import React from "react";

const Button = ({ type, children, onClick, className  }) => (
  <button
    onClick={onClick}
    className={className}
    type={type}
  >
    {children}
  </button>
);

export default Button;
