import React from "react";
import useUsers from "../../components/useUsers";
import { useTranslation } from "react-i18next";

const ApproveButton = ({ user }) => {
  const { sendRegistrationApproval } = useUsers();
  const { t } = useTranslation();

  return user.isApproved ? (
    <button type="button" className="btn btn-success" disabled>
      {t("approveRegistration")}
    </button>
  ) : (
    <button
      type="button"
      className="btn btn-success"
      onClick={() => sendRegistrationApproval(user)}
    >
      {t("approveRegistration")}
    </button>
  );
};

export default ApproveButton;
