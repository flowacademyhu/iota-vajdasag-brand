import React from 'react';
import { Button, Modal } from 'react-bootstrap'
import { useTranslation } from 'react-i18next'
import { Link } from 'react-router-dom'




const EventsProductButton = () => {
  const { t } = useTranslation()

  return (
    <>
      <Link to={{
        pathname: "/super-admin/events",
      search: "?sort=name",
      hash: "#the-hash",
      state: { fromDashboard: true }}}
      >
        <Button>
          {t("eventList.button")}
        </Button>
      </Link>
    </>
  );
}

export default EventsProductButton;
