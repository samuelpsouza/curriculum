import React from "react";
import styled from "styled-components";

const SideBar = styled.div`
    position: relative;
    width: 30%;
`;

const Menu = styled.ul`
    list-style-type: none;
`;

const MenuItem = styled.li`
    padding: 2%;
`;

export default () => {
    return (
        <SideBar>
            <Menu>
                <MenuItem>Programs</MenuItem>
                <MenuItem>Courses</MenuItem>
                <MenuItem>Users</MenuItem>
            </Menu>
        </SideBar>
    );
};
