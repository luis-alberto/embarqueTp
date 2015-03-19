#include <jni.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>


//					   Java_   nameSpace   _class       _method		    JVM			activity(thiz)
JNIEXPORT jstring JNICALL Java_fr_imie_imiendk_MainActivity_checkPrime(JNIEnv *env, jobject obj, jint nb){

	int i,j;
	if(nb == 1 || nb == 0)
		return (*env)->NewStringUTF(env, "N'est un nombre premier");
	i=2;
	while(i<nb){
		if(nb%i==0){
			return (*env)->NewStringUTF(env, "N'est un nombre premier");
		}
		i++;
	}
	return (*env)->NewStringUTF(env, "Est un nombre premier");
}

JNIEXPORT jintArray  JNICALL Java_fr_imie_imiendk_MainActivity_getAllPrimes(JNIEnv *env, jobject obj, jint nb){
	jintArray result;
	int i,j;
	int test;
	int * tabInt;
	int size = 0;

	i=2;
	while(i<=nb){
		test=0;
		for(j=2;j<=i-1;j++){
			if(i%j==0){
				test=1;
				break;
			}
		}
		if(test==0){
			size=size+1;
			if(sizeof(tabInt)==0){
				tabInt = malloc ( size * sizeof(int) );
			}else{
				tabInt = realloc (tabInt, size * sizeof(int) );
			}
			tabInt[size-1] = j;
		}
		i++;
	}

	if(sizeof(tabInt)!=0){
		result = (*env)->NewIntArray(env, size);
		(*env)->SetIntArrayRegion(env, result, 0, size, tabInt);
		return result;
	}else{
		return NULL;
	}

}
