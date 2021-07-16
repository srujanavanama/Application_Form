import React, {useState} from 'react';
import DropDown from './DropDown';
import AddApplicant from './AddApplicant';
import ViewApplication from './ViewApplication';
import ApplicationService from '../api/ApplicationService';

const ApplicationForm = () => {
    
  const [applicationType, setApplicationType] = useState("");
  const [amount, setAmount] = useState("");
  const [applicationStatus, setApplicationStatus] = useState("active");
  const [finalForm, setFinalForm] = useState({});
  const [applicantID, applicantIDGenerator] = useState(Math.floor(Math.random()*1000))
  
  const applicationTypeList = [{value:"home", label:"Home Loan"}, 
                                {value:"personal", label:"Personal Loan"}, 
                                {value:"business", label:"Business Loan"}]; 
                      
  const [applicants, setApplicants] = useState([{applicantId:applicantID ,firstName:"", lastName:"", gender:"", applicantIdTypes:[""]}])

  const isEnabled = true;
  const classCheck = false;

  const handleApplicationTypeChange = e => {
    setApplicationType(e.target.value)
  }

  const handleAmountChange = e => {
    setAmount(e.target.value)
  }

  const handleStatusChange = (e) => {
    setApplicationStatus(e.target.value)
  }

  const handleAddApplicant = () => {
    applicantIDGenerator(applicantID+1)
    setApplicants(applicants.concat([{ applicantId:applicantID+1, firstName:"", lastName:"", gender:"", applicantIdTypes:[""] }]))
  }

  const handleRemoveApplicant = id => () => {
    if(applicants.length>1) {
      setApplicants(applicants.filter((applicant, applicantId) => id !== applicant.applicantId))
    }
  }

  const handleApplicants = (applicant) => {
    const newAppList = applicants.filter((app, applicantId) => applicant.applicantId !== app.applicantId)
    newAppList.push(applicant)
    setApplicants(newAppList)
  }

  // canBeSubmitted() {
  //   const { email, password } = this.state;
  //   return (
  //     email.length > 0 &&
  //     password.length > 0
  //   );
  // }

  const handleSubmit = e => {
    if(!isEnabled) {
      e.preventDefault();
      e.stopPropagation();
      return;
    }
    e.preventDefault();
    const obj = {applicationType: applicationType, amount: amount, applicationStatus: applicationStatus, applicants: applicants}
    setFinalForm(obj)
    if(!Object.keys(obj).includes("applicationId")) {
        ApplicationService.submitApplication(obj)
    }
    
  }

  return (
    <div className="container">
      <div className="py-5 text-center">
        <h1>Welcome</h1>
        <h2>Application Form</h2>
      </div>
      <div>
      {(finalForm && Object.keys(finalForm).length > 0) ? <ViewApplication obj={finalForm} /> : 
          <div><small className="text-muted">.</small>
          <h4 className="mb-3">Application Details</h4>
        <form
          onSubmit={handleSubmit}
          className={classCheck ? "was-validated" : "needs-validation"}>
          <div className="mb-3">
            <DropDown
              title="Application Type"
              options={applicationTypeList}
              onChange={handleApplicationTypeChange}
             />
            <div className="invalid-feedback">Please select a valid application type</div>
          </div>
          <div className="mb-3">
            <label>Amount</label>
            <input 
              type="text" 
              className="form-control" 
              id="amount" 
              placeholder="Enter Amount in dollars"
              value={amount}
              onChange={handleAmountChange} 
              required />
            <div className="invalid-feedback">Please enter a valid amount</div>
          </div>
          <div className="mb-3">
            Status
            <div className="d-block my-3">
              <div className="custom-control custom-radio">
                <label className="align-middle">
                <input
                  type="radio"
                  value="active"
                  name="applicationStatus"
                  className="form-check-input"
                  checked={applicationStatus === "active"}
                  onChange={handleStatusChange} />
                  Active
                </label>
              </div>
              <div className="custom-control custom-radio">
                <label> 
                <input  
                  type="radio" 
                  value="inactive"
                  name="applicationStatus"
                  className="align-middle form-check-input"
                  checked={applicationStatus === "inactive"}
                  onChange={handleStatusChange} 
                   />
                  InActive
                </label>
              </div>
            </div>
          </div>
          {applicants.map((applicant, id) => (
            <div key={applicant.applicantId}>
              <hr className="mb-4"></hr>
              <h4 className="mb-3">
              Applicant Details
              <button 
                className="btn btn-outline-primary btn-sm float-right"
                type="button"
                disabled={applicants.length<2}
                onClick={handleRemoveApplicant(applicant.applicantId)}
              ><span className="align-middle material-icons">person_remove</span></button>
          </h4>
              <AddApplicant id={applicant.applicantId} handleApplicants={handleApplicants}/>
            </div>
          ))}
          <hr className="mb-4"></hr>
          <button 
            type="button"
            onClick={handleAddApplicant} 
            className="btn btn-outline-primary text-center">
              <span className="align-middle material-icons">person_add</span> Add Additional Applicant
          </button>
          <hr className="mb-4"></hr>
          <button 
            className="btn btn-primary btn-lg btn-block" 
            type="submit"
            disabled={!isEnabled}>
              Submit Application
          </button>
        </form>
        </div> }
      </div>
    </div>
  );
}

export default ApplicationForm;