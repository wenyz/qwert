cmake_minimum_required(VERSION 3.9)
project(jnitest)

set(CMAKE_CXX_STANDARD 11)

set(OpenCV_DIR "D:\\opencv\\opencv\\mingw64_build\\install")
#set(OpenCV_DIR "D:\\opencv\\opencv")
find_package(OpenCV REQUIRED)

set(CMAKE_MODULE_PATH ${CMAKE_MODULE_PATH} "${CMAKE_SOURCE_DIR}/cmake/")

#include_directories(${OpenCV_INCLUDE_DIRS})

add_executable(jnitest main.cpp )
add_executable(test2 test2.cpp )

set(OpenCV_LIBS opencv_core opencv_imgproc opencv_highgui opencv_imgcodecs)
#set(OpenCV_LIBS opencv_core)
target_link_libraries(jnitest ${OpenCV_LIBS})

add_executable(vectortest vector.cpp )
add_executable(op001 opcvl/op001.cpp )
target_link_libraries(op001 ${OpenCV_LIBS})

add_executable(op002 opcvl/op002.cpp )
target_link_libraries(op002 ${OpenCV_LIBS})

add_executable(op003 opcvl/op003.cpp )
target_link_libraries(op003 ${OpenCV_LIBS})

add_executable(op004 opcvl/op004.cpp )
target_link_libraries(op004 ${OpenCV_LIBS})

add_executable(op005 opcvl/op005.cpp )
target_link_libraries(op005 ${OpenCV_LIBS})

add_executable(op006 opcvl/op006.cpp )
target_link_libraries(op006 ${OpenCV_LIBS})

add_executable(op007 opcvl/op007.cpp )
target_link_libraries(op007 ${OpenCV_LIBS})

add_executable(op008 opcvl/op008.cpp )
target_link_libraries(op008 ${OpenCV_LIBS})

add_executable(op009 opcvl/op009.cpp )
target_link_libraries(op009 ${OpenCV_LIBS})

add_executable(op010 opcvl/op010.cpp )
target_link_libraries(op010 ${OpenCV_LIBS})


add_executable(se001 selft/se001.cpp )
target_link_libraries(se001 ${OpenCV_LIBS})

add_executable(scsp selft/consumep/scsp.cpp )
target_link_libraries(scsp ${OpenCV_LIBS})

add_executable(wlist common/wlist.c )
target_link_libraries(scsp ${OpenCV_LIBS})