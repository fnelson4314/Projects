import { initializeApp } from "firebase/app";
import { getFirestore } from "firebase/firestore";
import { getAuth, createUserWithEmailAndPassword } from "firebase/auth";

const firebaseConfig = {
  apiKey: "AIzaSyBQSHTqnFPWh8w053iHBzt1kITS6aN6Ps8",
  authDomain: "netflix-clone-9adfc.firebaseapp.com",
  projectId: "netflix-clone-9adfc",
  storageBucket: "netflix-clone-9adfc.firebasestorage.app",
  messagingSenderId: "712572092475",
  appId: "1:712572092475:web:78bb4153985fc9e59d9c1e",
};

initializeApp(firebaseConfig);
const db = getFirestore();
const auth = getAuth();

export { auth, createUserWithEmailAndPassword };
export default db;
