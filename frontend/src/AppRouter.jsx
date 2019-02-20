import React from 'react';
import { HashRouter, Route, Redirect, Switch } from 'react-router-dom'
import MajorList from './components/major/MajorList';
import CourseList from './components/course/CourseList';
import Users from './components/user/Users';
import Login from './components/user/Login';
import PrivateRoute from './PrivateRoute';

const AppRoutes = props => (
    <HashRouter>
        <Switch>
            <PrivateRoute exact path='/courses' component={CourseList}/>
            <PrivateRoute exact path='/users' component={Users} />
            <Route path='/majors' exact component={MajorList} />
            <Route path='/' exact component={Login} />
            <Route path='/login' exact component={Login} />
            <Redirect from='' to='/' />
        </Switch>
    </HashRouter>   
);

export default AppRoutes;