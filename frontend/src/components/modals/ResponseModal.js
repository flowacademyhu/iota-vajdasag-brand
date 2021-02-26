import React from 'react'
import { Modal, Button } from 'react-bootstrap'

const ResponseModal = ({ onClose, showResponseModal, title }) => {

  return (
    <Modal
      show={showResponseModal}
      onHide={onClose}
      animation={false}
    >
      <Modal.Body>{title}</Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={onClose}>
          {title}
        </Button>
      </Modal.Footer>
    </Modal>
  )
}

export default ResponseModal
