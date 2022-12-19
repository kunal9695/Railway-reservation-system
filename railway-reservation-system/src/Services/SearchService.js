import axios from'axios'
import authHeader from './auth-header';
const TRAIN_SEARCH_ALL_URL ='http://localhost:8080/admin/all';
const TRAIN_SEARCH_ALL_USER_URL ='http://localhost:8080/users/alltrains';
const TRAIN_SEARCH_BYROUTE = 'http://localhost:8082/users/route'
const TRAIN_ADD_TRAIN_URL ='http://localhost:8080/admin/addtrain';
const TRAIN_GET_TRAIN_BY_ID_URL ='http://localhost:8080/admin/search';
const TRAIN_UPDATE_TRAIN_BY_ID_URL ='http://localhost:8080/admin/update';
const TRAIN_DELETE_TRAIN_BY_ID_URL ='http://localhost:8080/admin/delete';



class SearchService{

getAllTrains(){
    return axios.get(TRAIN_SEARCH_ALL_URL, { headers: authHeader() });
}

getAllTrainsUsers(){
    console.log(axios.get(TRAIN_SEARCH_ALL_USER_URL, { headers: authHeader() }));
    return axios.get(TRAIN_SEARCH_ALL_USER_URL, { headers: authHeader() });
}

getTrainsByRoute(sourceStation,destinationStation){
    console.log(axios.get(TRAIN_SEARCH_BYROUTE +'/'+ sourceStation+'/'+ destinationStation))
    return axios.get(TRAIN_SEARCH_BYROUTE +'/'+ sourceStation+'/'+ destinationStation);
}

addTrain(trainDetails){
    console.log(axios.post(TRAIN_ADD_TRAIN_URL ,trainDetails, { headers: authHeader() }))
    return axios.post(TRAIN_ADD_TRAIN_URL ,trainDetails, { headers: authHeader() });
}

getTrainById(trainNo){
    return axios.get(TRAIN_GET_TRAIN_BY_ID_URL + '/'+ trainNo,{ headers: authHeader() });
}

updateTrain(trainNo,TrainDetails){
    return axios.put(TRAIN_UPDATE_TRAIN_BY_ID_URL + '/'+ trainNo ,TrainDetails, { headers: authHeader() });
}

deleteTrain(trainNo){
    return axios.delete(TRAIN_DELETE_TRAIN_BY_ID_URL + '/' + trainNo, { headers: authHeader() });
}

}
export default new SearchService();
