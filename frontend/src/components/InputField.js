import {Field, useField } from "formik";



export default ({ label, ...props }) => {
    const [field, meta, helpers] = useField(props.name);
    const showError = meta.touched && meta.error;

    return (
        <>
            <label htmlFor={props.name}>{label}</label>
            <Field
                {...field}
                {...props}
                className={`form-control ${showError ? "is-invalid" : ""}`}
            ></Field>
            {showError && <div className="invalid-feedback">{meta.error}</div>}
        </>
    );


}