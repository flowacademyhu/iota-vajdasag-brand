import { Modal, Button } from "react-bootstrap";

export default function MyVerticallyCenteredModal(props) {
  return (
    <Modal
      {...props}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
      animation={false}
    >
      <Modal.Title id="contained-modal-title-vcenter">
        {props.modalTitle}
      </Modal.Title>
      {/* </Modal.Header> */}
      <Modal.Body>
        <p>{props.modalBody}</p>
      </Modal.Body>
      <Modal.Footer>
        <Button onClick={props.onHide}>{props.modalButton}</Button>
      </Modal.Footer>
    </Modal>
  );
}
