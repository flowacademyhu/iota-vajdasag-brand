import React from "react";

const Button = ({ children, ...props  }) => (
  <button
  {...props}
  >
  {/* //  onClick={onClick}
  //   className={className}
  //   type={type} */}
    {children}
  </button>
);

export default Button;
