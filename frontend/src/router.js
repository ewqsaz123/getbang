
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import ReservationManager from "./components/listers/ReservationCards"
import ReservationDetail from "./components/listers/ReservationDetail"

import ScheduleManager from "./components/listers/ScheduleCards"
import ScheduleDetail from "./components/listers/ScheduleDetail"


import ReservationStatusViewView from "./components/ReservationStatusViewView"
import ReservationStatusViewViewDetail from "./components/ReservationStatusViewViewDetail"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/reservations',
                name: 'ReservationManager',
                component: ReservationManager
            },
            {
                path: '/reservations/:id',
                name: 'ReservationDetail',
                component: ReservationDetail
            },

            {
                path: '/schedules',
                name: 'ScheduleManager',
                component: ScheduleManager
            },
            {
                path: '/schedules/:id',
                name: 'ScheduleDetail',
                component: ScheduleDetail
            },


            {
                path: '/reservationStatusViews',
                name: 'ReservationStatusViewView',
                component: ReservationStatusViewView
            },
            {
                path: '/reservationStatusViews/:id',
                name: 'ReservationStatusViewViewDetail',
                component: ReservationStatusViewViewDetail
            },


    ]
})
