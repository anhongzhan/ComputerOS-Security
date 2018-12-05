#include <unistd.h>  
int main(int argc, char *argv[], char *env[])  
{  
        execle("/bin/ls","ls","-l", "/etc",(char *)0,env);  
        return 0;  
}  
