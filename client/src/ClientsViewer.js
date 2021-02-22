import React, {useState, useEffect, useMemo} from "react";
import axios from "axios";
import {useTable} from "react-table";
import {Link} from "react-router-dom";

export const ClientsViewer = (props) => {
    const [clients, setClients] = useState([])

    useEffect(() => {
        axios.get("/bank/clients").then((response) => {
            setClients(response.data)
        })
    }, [])

    const columns = useMemo(() => [
        {
            Header: "ID",
            accessor: "id"
        },
        {
            Header: "Идентификационный номер",
            accessor: "passport.idNumber"
        },
        {
            Header: "Имя",
            accessor: "firstName"
        },
        {
            Header: "Фамилия",
            accessor: "secondName"
        },
        {
            Header: "Отчество",
            accessor: "patronymic"
        },
        {
            Header: "Дата рождения",
            accessor: "birthDate"
        },
        {
            Header: "E-mail",
            accessor: "email"
        },
        {
            Header: "",
            accessor: "edit"
        }
    ], [])

    const tableData = React.useMemo(
        () => (clients.map(client => ({
            ...client,
            edit: <Link to={`/clients/${client.id}`}>
                <button style={{width: 100, height: 30}}>Подробнее</button>
            </Link>
        }))),
        [clients]
    )

    const {
        getTableProps,
        getTableBodyProps,
        headerGroups,
        rows,
        prepareRow,
    } = useTable({columns, data: tableData})

    return (
        <div style={{padding: 20}}>
            <div style={{display: "flex", justifyContent: "space-between", alignItems: "center", padding: 10}}>
                <h1>Список клиентов</h1>
                <Link to="/clients/new">
                    <button style={{width: 100, height: 30}}>Создать</button>
                </Link>
            </div>
            <table {...getTableProps()}
                   style={{width: "100%", margin: 10, padding: 10, border: "solid 1px white", borderRadius: 5, textAlign: "center"}}>
                <thead>
                {headerGroups.map(headerGroup => (
                    <tr {...headerGroup.getHeaderGroupProps()}>
                        {headerGroup.headers.map(column => (
                            <th
                                {...column.getHeaderProps()}
                            >
                                {column.render('Header')}
                            </th>
                        ))}
                    </tr>
                ))}
                </thead>
                <tbody {...getTableBodyProps()}>
                {rows.map(row => {
                    prepareRow(row)
                    return (
                        <tr {...row.getRowProps()}>
                            {row.cells.map(cell => {
                                return (
                                    <td
                                        {...cell.getCellProps()}
                                    >
                                        {cell.render('Cell')}
                                    </td>
                                )
                            })}
                        </tr>
                    )
                })}
                </tbody>
            </table>
        </div>
    )
}