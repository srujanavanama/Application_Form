import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import HeaderComponent from './components/HeaderComponent';
import ApplicationForm from './components/ApplicationForm';
import ViewApplication from './components/ViewApplication';
import AllApplications from './components/AllApplications';

const App = () => {
  return (
    <div className="App">
      <Router>
        <HeaderComponent />
        <Switch>
          <Route path="/" exact component={AllApplications} />
          <Route path="/view" component={ViewApplication} />
          <Route path="/new" component={ApplicationForm} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;