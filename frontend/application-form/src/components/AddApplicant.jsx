import React, {useState} from 'react';
import { Multiselect } from 'multiselect-react-dropdown';
import DropDown from './DropDown';

const AddApplicant = ({ id, handleApplicants}) => {

    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [gender, setGender] = useState("");
    const [applicantIDType, setApplicantIDType] = useState([""]);
    const [enableConfirm, setenableConfirm] = useState(true);
    const [classCheck, setClassCheck] = useState(false);

    const applicantIDTypeList = ["Australian Passport", "Driver License", "Foreign Passport", "Foreign ID Card"];

    const genderList = [{value:"male", label:"Male"}, 
                      {value:"female", label:"Female"}, 
                      {value:"na", label:"Not Disclosed"}];  

    const handleFirstNameChange = e => {
        setFirstName(e.target.value);
        setenableConfirm(false)
      }
    
    const handleLastNameChange = e => {
        setLastName(e.target.value)
        setenableConfirm(false)
      }
    
    const handleGenderChange = e => {
        setGender(e.target.value)
        setenableConfirm(false)
      }

    const handleApplicantIDType = selectedList => {
      setApplicantIDType(selectedList)
      setenableConfirm(false)
    }

    const confirmApplicant = (e) => {
      if(firstName && lastName && gender && applicantIDType && firstName.length>3 && lastName.length>3 && gender.length>0 && applicantIDType.length>0) {
        e.preventDefault();
        handleApplicants({applicantId:id, firstName:firstName, lastName:lastName, gender:gender, applicantIdTypes:applicantIDType})
        setenableConfirm(true)
      } else {
        e.preventDefault();
        setClassCheck(true)
        return
      }
    }

    return (
        <div className={classCheck ? "was-validated" : "needs-validation"}>
          <div className="row">
            <div className="col-md-6 mb-3">
              <label>First Name</label>
              <input 
                type="text" 
                className="form-control"
                placeholder="Enter First Name"
                value={firstName}
                onChange={handleFirstNameChange} 
                required />
              <div className="invalid-feedback">Valid First Name is required.</div>
            </div>
            <div className="col-md-6 mb-3">
            <label>Last Name</label>
              <input 
                type="text" 
                className="form-control" 
                placeholder="Enter Last Name"
                value={lastName}
                onChange={handleLastNameChange}
                required />
              <div className="invalid-feedback">Valid Last Name is required.</div>
            </div>
          </div>
          <div className="mb-3">
            <DropDown
              title="Gender"
              options={genderList}
              onChange={handleGenderChange} />
            <div className="invalid-feedback">Please select a valid application type</div>
          </div>
          <div className="mb-3">
          <label>Applicant ID Type</label>
            <Multiselect
              options={applicantIDTypeList}
              isObject={false}
              onSelect={handleApplicantIDType}
              onRemove={handleApplicantIDType}
              showArrow
              avoidHighlightFirstOption
              selectionLimit="2"
             />
            <div className="invalid-feedback">Please select a valid application type</div>
          </div>
          <div className="inline" >
          <button style={{display:'inline'}} className="btn btn-primary" disabled={enableConfirm} onClick={confirmApplicant}>Confirm</button>
      Please confirm the applicant before submitting the form
          </div>
        </div>
    )
}

export default AddApplicant;