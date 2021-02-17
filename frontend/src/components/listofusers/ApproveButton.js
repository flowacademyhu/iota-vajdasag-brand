import React from "react";
import useUsers from "../../components/useUsers";
import { useTranslation } from "react-i18next";
import { Button } from "react-bootstrap";

const ApproveButton = ({ user }) => {
  const { sendRegistrationApproval } = useUsers();
  const { t } = useTranslation();

  return !user.isApproved ? (
    <Button
      type="button"
      variant="success"
      onClick={() => sendRegistrationApproval(user.id)}
    >
      {t("approveRegistration")}
    </Button>
  ) : (
    ""
  );
};

export default ApproveButton;
