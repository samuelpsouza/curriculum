import React from 'react';
import { Router, Route, Redirect, Switch } from 'react-router-dom'
import MajorList from './components/major/MajorList';
import CourseList from './components/course/CourseList';
import Users from './components/user/Users';
import Login from './components/user/Login';
import PrivateRoute from './PrivateRoute';

const AppRoutes = ({ history, user }) => (
    <Router history={history}>
        <Switch>
            <Route path='/' exact component={Login} />
            <Route path='/login' exact component={Login} />
            <Route path='/majors' exact component={MajorList} />

            {/* Private Routes */}

            <PrivateRoute exact path='/courses' isAuthenticated={user.isAuthenticated} component={CourseList}/>
            <PrivateRoute exact path='/users' isAuthenticated={user.isAuthenticated} component={Users} />

            <Redirect from='' to='/login' />
        </Switch>
    </Router>   
);

export default AppRoutes;