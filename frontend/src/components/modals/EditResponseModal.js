import React from 'react'
import { Modal, Button } from 'react-bootstrap'
import { useTranslation } from 'react-i18next/'

const EditResponseModal = ({
  setShowResponseModal,
  showResponseModal,
  title,
  onClose,
}) => {
  const { t } = useTranslation()

  return (
    <Modal
      show={showResponseModal}
      onHide={() => setShowResponseModal(false)}
      animation={false}
    >
      <Modal.Body>{t(title)} </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={onClose}>
          {title}
        </Button>
      </Modal.Footer>
    </Modal>
  )
}

export default EditResponseModal
