import React, { useState } from "react";
import useUsers from "./useUsers";
import ListElement from "./listofusers/ListElement";
import Searchbar from "./listofusers/Searchbar";
import ListHeader from "./listofusers/ListHeader";

const UsersList = () => {
  const [searchKeyword, setSearchKeyword] = useState("");
  const [sortKey, setSortKey] = useState("");
  const [isSortAscending, setAscendingSort] = useState(true);
  const { users } = useUsers(searchKeyword);

  const sortColumn = (a, b) => {
    if (isSortAscending) {
      return a[sortKey] > b[sortKey] ? 1 : -1;
    } else {
      return a[sortKey] < b[sortKey] ? 1 : -1;
    }
  };

  return (
    <div className="d-flex flex-row-reverse">
      <div className="col-9">
        <Searchbar setSearchKeyword={setSearchKeyword} />
        <table className="table table-striped">
          <ListHeader
            setSortKey={setSortKey}
            setAscendingSort={setAscendingSort}
            isSortAscending={isSortAscending}
          />
          <tbody>
            {users
              ?.sort((a, b) => sortColumn(a, b))
              .map((user) => (
                <ListElement user={user} key={user.id} />
              ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default UsersList;
