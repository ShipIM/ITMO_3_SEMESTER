import {createRouter, createWebHistory} from "vue-router"
import loginPage from "@/components/login-page";
import registerPage from "@/components/register-page";
import mainPage from "@/components/main-page";

const routes = [
    {path: '/main', component: mainPage, alias: '/', name: 'main'},
    {path: '/login', component: loginPage, name: 'login'},
    {path: '/register', component: registerPage, name: 'register'}
];

export const router = createRouter({
    history: createWebHistory(),
    routes
})