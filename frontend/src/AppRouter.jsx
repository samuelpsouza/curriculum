import React from 'react';
import { HashRouter, Route, Redirect, Switch } from 'react-router-dom'
import MajorList from './components/major/MajorList';
import CourseList from './components/course/CourseList';
import Users from './components/user/Users';

const AppRoutes = props => (
    <HashRouter>
        <Switch>
            <Route path='/' exact component={MajorList} />
            <Route path='/courses' exact component={CourseList} />
            <Route path='/users' component={Users} />
            <Redirect from='' to='/' />
        </Switch>
    </HashRouter>   
);

export default AppRoutes;