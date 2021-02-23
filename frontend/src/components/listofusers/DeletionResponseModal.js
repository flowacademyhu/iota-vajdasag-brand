import React from 'react'
import { Modal, Button } from 'react-bootstrap'
import { useTranslation } from 'react-i18next/'

const DeletionResponseModal = ({ setShowResponseModal, showResponseModal, title }) => {
  const { t } = useTranslation()

  return (
    <Modal
      show={showResponseModal}
      onHide={() => setShowResponseModal(false)}
      animation={false}
    >
      <Modal.Body>{title}</Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={() => setShowResponseModal(false)}>
          {t('userListElement.close')}
        </Button>
      </Modal.Footer>
    </Modal>
  )
}

export default DeletionResponseModal
