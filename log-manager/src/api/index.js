/* eslint-disable */

import{get,post} from './http'



// 管理员的验证信息
export const getLoginStatus = (params) => post("user/login",params);


// ================================用户数据统计的接口===============================

//统计用户的地域信息
export const statisticArea = () => get(`/logdau/area`);

// 统计用户的在线时长信息
export const statisticHour = () => get(`/logdau/hour`);

// 统计用户的手机来源
export const statisticMobile = () => get(`/logdau/mobile`);

// 统计用户的下单来源，具体就是用户在那个应用商店下载的内容
export const statisticOrigin = () => get(`/logdau/origin`);


// 用户点击信息的统计，包括评论和最喜欢等等的信息
export const statisticUser = () => get(`/logsact/all`);

