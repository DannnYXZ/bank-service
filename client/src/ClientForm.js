import React, {useState, useEffect} from "react";
import {Field, Form, Formik, useField} from "formik";
import * as Yup from "yup";
import axios from "axios";
import {useHistory} from "react-router-dom"
import "./Form.css";

const TextInput = ({label, ...props}) => {
    const [field, meta] = useField(props);
    return (
        <div className="text-input">
            <label htmlFor={props.id || props.name}>{label}</label>
            <input {...field} {...props} />
            {meta.touched && meta.error ? (
                <div className="error">{meta.error}</div>
            ) : null}
        </div>
    )
}

const SelectInput = ({label, children, ...props}) => {
    const [field, meta] = useField(props);
    return (
        <div className="text-input">
            <label htmlFor={props.id || props.name}>{label}</label>
            <select {...field} {...props} >
                {children}
            </select>
            {meta.touched && meta.error ? (
                <div className="error">{meta.error}</div>
            ) : null}
        </div>
    )
}

export const ClientForm = ({match}) => {
    const history = useHistory();
    const isNew = match.params.clientId !== "new";
    const [suggestions, setSuggestions] = useState({
        cities: [],
        maritalStatuses: [],
        citizenship: [],
        disabilities: []
    })
    const [initialValues, setInitialValues] = useState({
        firstName: "",
        secondName: "",
        patronymic: "",
        birthDate: "",
        birthPlace: "",
        gender: "",
        email: "",
        notEmptyString: "",
        liveAddress: "",
        passportIssueDate: "",
        passportSeries: "",
        passportNumber: "",
        idNumber: "",
        passportIssuer: "",
        residenceCityId: "",
        residenceAddress: "",
        maritalStatusId: "",
        citizenshipId: "",
        disabilityId: ""
    });
    useEffect(() => {
        axios.get("/bank/clients/form/suggestions").then((response) => {
            console.log(response.data);
            setSuggestions(response.data);
        })
    }, [])
    const loadClientData = (clientId) => {
        axios.get(`/bank/clients/${clientId}/shallow`).then((response) => {
            setInitialValues({
                    ...initialValues,
                    ...response.data
                }
            )
        })
    }
    useEffect(() => {
        if (isNew) {
            loadClientData(match.params.clientId);
        }
    }, [])
    const onSubmit = (values) => {
        axios.post("/bank/clients", values).then((response) => {
                history.goBack();
            },
            (error) => {
                alert("Не удалось сохранить клиента");
            })
    }
    const nameValidation = Yup.string().trim().matches(/\p{L}/u, "Поле должно содержать буквы").required("Обязятельно");
    const notEmptyString = Yup.string().trim().required("Обязятельно");
    const validationSchema = Yup.object().shape({
        firstName: nameValidation,
        secondName: nameValidation,
        patronymic: nameValidation,
        birthDate: Yup.date("Некорректная дата").required("Обязятельно"),
        birthPlace: notEmptyString,
        gender: notEmptyString,
        passportSeries: Yup.string().matches(/^[0-9A-Z]+$/, "Только цифры и заглавные латинские буквы").required("Обязятельно"),
        passportNumber: notEmptyString,
        passportIssuer: notEmptyString,
        idNumber: Yup.string().matches(/^[0-9A-Z]+$/, "Только цифры и заглавные латинские буквы").required("Обязятельно"),
        passportIssueDate: Yup.date("Некорректная дата").required("Обязятельно"),
        email: Yup.string().email("Неверный адрес").required("Обязятельно"),
        liveCityId: notEmptyString,
        liveAddress: notEmptyString,
        residenceCityId: notEmptyString,
        residenceAddress: notEmptyString,
        maritalStatusId: notEmptyString,
        citizenshipId: notEmptyString,
        disabilityId: notEmptyString,
        phoneHome: Yup.string().matches(/\d/, "Только цифры").nullable(),
        phoneMobile: Yup.string().matches(/^([+]\d{2})?\d{10}$/, "Формат должен быть как +918087339090").nullable(),
    })
    const deleteClient = (clientId) => {
        axios.delete(`/bank/clients/${clientId}`).then((response) => {
            history.goBack()
        })
    }
    return (
        <div style={{display: "flex", justifyContent: "center"}}>
            <Formik {...{
                initialValues,
                enableReinitialize: true,
                validationSchema,
                onSubmit
            }}>
                {({errors, touched, isValidating}) =>
                    <Form style={{width: "50%"}}>
                        <section style={{display: "flex", justifyContent: "space-between"}}>
                            <section style={{display: "flex", flexDirection: "column", width: "45%"}}>
                                <h1>Персональные данные</h1>
                                <TextInput label="Имя" name="firstName"/>
                                <TextInput label="Фамилия" name="secondName"/>
                                <TextInput name="patronymic" label="Отчество"/>
                                <TextInput name="birthDate" placeholder="ГГГГ-ММ-ДД" label="Дата рождения"/>
                                <TextInput name="birthPlace" label="Место рождения"/>
                                <div id="gender-radio-group">Пол</div>
                                <div role="group" aria-labelledby="gender-radio-group" style={{marginBottom: 10}}>
                                    <label>
                                        <Field type="radio" name="gender" value="MALE"/>
                                        Мужской
                                    </label>
                                    <label>
                                        <Field type="radio" name="gender" value="FEMALE"/>
                                        Женский
                                    </label>
                                    {errors.gender && touched.gender && <div className="error">{errors.gender}</div>}
                                </div>
                                <label>Город фактического проживания</label>
                                <SelectInput as="select" name="liveCityId">
                                    <option value="" label="Выбрать город"/>
                                    {[suggestions.cities.map(city => <option value={city.id} label={city.name}/>)]}
                                </SelectInput>
                                <TextInput name="liveAddress" label="Адрес фактического проживания"/>
                                <TextInput name="phoneHome" label="Телефон домашний"/>
                                <TextInput name="phoneMobile" label="Телефон мобильный"/>
                                <label>E-mail</label>
                                <TextInput name="email" type="email"/>
                                <label>Город прописки</label>
                                <SelectInput name="residenceCityId" label="">
                                    <option value="" label="Выбрать город"/>
                                    {[suggestions.cities.map(city => <option value={city.id} label={city.name}/>)]}
                                </SelectInput>
                                <TextInput name="residenceAddress" label="Адрес прописки"/>
                                <label>Семейное положение</label>
                                <SelectInput name="maritalStatusId">
                                    <option value="" label="Выбрать статус"/>
                                    {[suggestions.maritalStatuses.map(status => <option value={status.id} label={status.status}/>)]}
                                </SelectInput>
                                <label>Гражданство</label>
                                <SelectInput name="citizenshipId">
                                    <option value="" label="Выбрать гражданство"/>
                                    {[suggestions.citizenship.map(citizenship => <option value={citizenship.id}
                                                                                         label={citizenship.name}/>)]}
                                </SelectInput>
                                <label>Инвалидность</label>
                                <SelectInput name="disabilityId" as="select">
                                    <option value="" label="Выбрать"/>
                                    {[suggestions.disabilities.map(
                                        disability => <option value={disability.id}
                                                              label={disability.name}>{disability.description}</option>
                                    )]}
                                </SelectInput>
                                <label>Пенсионер</label>
                                <Field type="checkbox" name="retiree"/>
                                <label>Ежемесячный доход</label>
                                <div style={{display: "flex"}}>
                                    <TextInput name="monthlyIncomeValue" style={{marginRight: 10}}/>
                                    <Field name="monthlyIncomeCurrency" as="select" style={{width: 50}}>
                                        {["", "BYN", "USD", "EUR", "RUB"].map(s => <option label={s} value={s}/>)}
                                    </Field>
                                </div>
                            </section>
                            <section style={{width: "45%"}}>
                                <h1>Паспорт</h1>
                                <TextInput name="passportSeries" label="Серия паспорта"/>
                                <TextInput name="passportNumber" label="Номер паспорта"/>
                                <TextInput name="passportIssuer" label="Кем выдан"/>
                                <TextInput name="passportIssueDate" placeholder="ГГГГ-ММ-ДД" label="Дата выдачи"/>
                                <TextInput name="idNumber" placeholder="1234567A890PB1" label="Идентификационный номер"/>
                            </section>
                        </section>
                        <section>
                            <button type="button" style={{marginRight: 10, height: 30}} onClick={() => history.goBack()}>Назад</button>
                            <button type="submit" style={{marginRight: 10, height: 30}}>Сохранить</button>
                            {isNew &&
                            <button type="button" style={{marginRight: 10, height: 30}}
                                    onClick={() => deleteClient(match.params.clientId)}>
                                Удалить
                            </button>}
                        </section>
                    </Form>
                }
            </Formik>
        </div>
    )
}