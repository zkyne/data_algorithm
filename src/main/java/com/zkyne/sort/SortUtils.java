package com.zkyne.sort;

/**
 * @ClassName: SortUtils
 * @Description: 排序工具类
 * @Author: zkyne
 * @Date: 2018/3/29 15:35
 */
public class SortUtils {

    /**
     * 选择排序
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            int min = i;//每一趟循环比较时，min用于存放较小元素的数组下标，这样当前批次比较完毕最终存放的就是此趟内最小的元素的下标，避免每次遇到较小元素都要进行交换。
            for (int j = i + 1; j < length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            //进行交换，如果min发生变化，则进行交换
            if (min != i) {
                swap(arr, min, i);
            }
        }

    }

    /**
     * 冒泡排序
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            //设定一个标记，若此次循环没有进行交换，也就是待排序列已经有序，排序已然完成。跳出排序
            boolean flag = true;
            for (int j = 0; j < length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }

    }

    /**
     * 插入排序
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            // 默认把前面的当做已经是有序的
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) {
                swap(arr, j, j - 1);
                j--;
            }
        }
    }

    /**
     * 插入排序(节选自Arrays.sort方法内部实现)
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void insertSort(int[] arr, int left, int right) {
        rangeCheck(arr.length, left, right);
        for (int i = left, j = i; i < right; j = ++i) {
            int temp = arr[i + 1];
            while (temp < arr[j]) {
                arr[j + 1] = arr[j];
                if (j-- == left) break;
            }
            arr[j + 1] = temp;
        }
    }


    /**
     * 希尔排序
     *
     * @param arr
     */
    public static void hillSort(int[] arr) {
        int length = arr.length;
        int h = 1;
        // 设置初始最大步长
        while (h < length / 3) h = h * 3 + 1;
        while (h >= 1) {
            // 从第二个元素开始
            for (int i = 1; i < length; i++) {
                // 从第i个元素开始，依次次和前面已经排好序的i-h个元素比较，如果小于，则交换
                int j = i;
                // 只有小于前面的才进行,比较大的就已经是较大的了
                while (j >= h && arr[j] < arr[j - h]) {
                    swap(arr, j, j - h);
                    j -= h;
                }
            }
            // 步长除3递减
            h = h / 3;
        }
    }

    /**
     * 合并排序
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * 合并排序
     *
     * @param arr
     */
    public static void mergeSort(int[] arr, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + ((hi - lo) >>> 1);//平分数组
        // 递归对左侧元素进行排序
        mergeSort(arr, lo, mid);
        // 递归对右侧元素进行排序
        mergeSort(arr, mid + 1, hi);
        // 如果左侧最大小于右侧最小就不用进行合并了
        if (arr[mid] <= arr[mid + 1]) return;
        // 对左右排序好的进行合并
        merge(arr, lo, mid, hi);//对左右排好的序列进行合并
    }

    /**
     * 快速排序
     * @param arr
     */
    public static void swiftSort(int[] arr) {
        swiftSort(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序
     *
     * @param arr
     */
    public static void swiftSort(int[] arr, int lo, int hi) {
        //如果子序列为1，则直接返回
        if (lo >= hi) return;
        // 当元素小于10个的时候采用插入排序算法
        if (hi - lo <= 9) {
            insertSort(arr);
            return;
        }
        // 采用三平均分区法查找中间数
        int mid = MedianOf3(arr, lo, lo + ((hi - lo) >>> 1), hi);
        swap(arr, lo, mid);
        //划分，划分完成之后，分为左右序列，左边所有元素小于arr[index]，右边所有元素大于arr[index]
        int index = partition(arr, lo, hi);

        //对左右子序列进行排序完成之后，整个序列就有序了
        //对左边序列进行递归排序
        swiftSort(arr, lo, index - 1);
        //对右边序列进行递归排序
        swiftSort(arr, index + 1, hi);
    }

    private static int partition(int[] arr, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            //从右自左扫描，如果碰到比基准元素arr[lo]大，则该元素已经位于正确的分区，j自减，继续比较j-1
            //否则，退出循环，准备交换
            while (arr[--j] > arr[lo]) {
                //如果扫描到了最左端，退出循环
                if (j == lo) break;
            }
            //从左至右扫描，如果碰到比基准元素arr[lo]小，则该元素已经位于正确的分区，i自增，继续比较i+1；
            //否则，退出循环，准备交换
            while (arr[++i] < arr[lo]) {
                //如果扫描到了最右端，退出循环
                if (i == hi) break;
            }
            //如果相遇，退出循环
            if (i >= j) break;
            //交换左a[i],a[j]右两个元素，交换完后他们都位于正确的分区
            swap(arr, i, j);
        }
        //经过相遇后，最后一次a[i]和a[j]的交换
        //a[j]比a[lo]小，a[i]比a[lo]大，所以将基准元素与a[j]交换
        swap(arr, lo, j);
        //返回扫描相遇的位置点
        return j;
    }

    /**
     * 三平均分区法
     *
     * @param arr
     * @param lo
     * @param center
     * @param hi
     * @return
     */
    private static int MedianOf3(int[] arr, int lo, int center, int hi) {
        return (arr[lo] < arr[center]) ? (arr[center] < arr[hi] ? center : arr[lo] < arr[hi] ? hi : lo) : (arr[hi] < arr[center] ? center : arr[hi] < arr[lo] ? hi : lo);
    }

    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static void merge(int[] arr, int lo, int mid, int hi) {
        int[] aux = new int[arr.length];
        int i = lo, j = mid + 1;
        //把元素拷贝到辅助数组中
        int k = lo;
        while (k <= hi) {
            aux[k] = arr[k];
            k++;
        }
        //然后按照规则将数据从辅助数组中拷贝回原始的array中
        for (k = lo; k <= hi; k++) {
            //如果左边元素没了， 直接将右边的剩余元素都合并到到原数组中
            if (i > mid) {
                arr[k] = aux[j++];
            }//如果右边元素没有了，直接将所有左边剩余元素都合并到原数组中
            else if (j > hi) {
                arr[k] = aux[i++];
            }//如果左边右边小，则将左边的元素拷贝到原数组中
            else if (aux[i] < aux[j]) {
                arr[k] = aux[i++];
            } else {
                arr[k] = aux[j++];
            }
        }
    }

    /**
     * 互换元素
     *
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        if (i == j) return;
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }

    /**
     * 校验数组是否越界
     *
     * @param arrayLength
     * @param fromIndex
     * @param toIndex
     */
    private static void rangeCheck(int arrayLength, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException(
                    "fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
        if (fromIndex < 0) {
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        }
        if (toIndex >= arrayLength) {
            throw new ArrayIndexOutOfBoundsException(toIndex);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 3, 4, 2, 1, 2};
//        hillSort(arr);
//        mergeSort(arr, 0, arr.length - 1);
//        swiftSort(arr, 0, arr.length - 1);
//        insertSort(arr);
        print(arr);
    }
}
