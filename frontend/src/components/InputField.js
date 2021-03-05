import { Field, useField } from 'formik'

const InputField = ({ label, hidden = false, ...props }) => {
  const [field, meta] = useField(props.name)
  const showError = meta.touched && meta.error

  return (
    <div className={hidden ? 'visually-hidden' : ''}>
      <label htmlFor={props.name}>{label}</label>
      <Field
        {...field}
        {...props}
        placeholder={label}
        className={`form-control ${showError ? 'is-invalid' : ''}`}
      ></Field>
      {showError && <div className="invalid-feedback">{meta.error}</div>}
    </div>
  )
}

export default InputField
