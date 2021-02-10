import React from "react";

const Button = ({ type, children, onClick }) => (
  <button className="btn btn-primary btn-block col-12" type={type}>
    {children}
  </button>
);

export default Button;
