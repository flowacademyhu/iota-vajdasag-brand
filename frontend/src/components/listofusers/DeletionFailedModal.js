import React from 'react'
import { Modal, Button } from 'react-bootstrap'
import { useTranslation } from 'react-i18next/'

const DeletionFailedModal = ({ setShowFailureModal, showFailureModal }) => {
  const { t } = useTranslation()

  return (
    <Modal
      show={showFailureModal}
      onHide={() => setShowFailureModal(false)}
      animation={false}
    >
      <Modal.Body>{t('userListElement.unsuccessful')}</Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={() => setShowFailureModal(false)}>
          {t('userListElement.close')}
        </Button>
      </Modal.Footer>
    </Modal>
  )
}

export default DeletionFailedModal
