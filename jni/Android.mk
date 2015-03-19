LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := prime-jni
LOCAL_SRC_FILES := prime-jni.c

include $(BUILD_SHARED_LIBRARY)