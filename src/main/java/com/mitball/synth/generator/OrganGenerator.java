package com.mitball.synth.generator;

public class OrganGenerator extends WaveTableGenerator {
    public OrganGenerator() {
        super(TABLE);
    }

    private static final int[] TABLE = new int[]{0, 1537, 3070, 4596, 6110,
            7609, 9089, 10547, 11979, 13381, 14751, 16086, 17381, 18636, 19846,
            21010, 22126, 23190, 24203, 25161, 26064, 26911, 27700, 28431,
            29104, 29718, 30273, 30770, 31210, 31592, 31918, 32188, 32405,
            32570, 32683, 32748, 32767, 32740, 32671, 32561, 32414, 32232,
            32017, 31773, 31501, 31205, 30887, 30550, 30198, 29831, 29454,
            29069, 28679, 28285, 27890, 27497, 27108, 26725, 26349, 25983,
            25628, 25286, 24959, 24646, 24350, 24072, 23812, 23570, 23348,
            23146, 22964, 22801, 22658, 22535, 22431, 22346, 22279, 22229,
            22197, 22180, 22178, 22190, 22215, 22251, 22298, 22355, 22419,
            22491, 22568, 22650, 22735, 22822, 22910, 22998, 23084, 23168,
            23249, 23326, 23397, 23463, 23522, 23574, 23618, 23654, 23682,
            23700, 23710, 23710, 23700, 23682, 23654, 23616, 23570, 23515,
            23452, 23381, 23302, 23216, 23124, 23025, 22921, 22812, 22698,
            22581, 22461, 22338, 22214, 22088, 21961, 21835, 21708, 21582,
            21458, 21335, 21214, 21096, 20981, 20868, 20758, 20652, 20549,
            20450, 20354, 20261, 20172, 20085, 20002, 19921, 19843, 19767,
            19693, 19621, 19549, 19478, 19408, 19338, 19267, 19195, 19121,
            19046, 18968, 18888, 18805, 18718, 18627, 18532, 18432, 18328,
            18218, 18104, 17984, 17858, 17726, 17589, 17445, 17296, 17141,
            16980, 16813, 16641, 16464, 16281, 16094, 15902, 15705, 15505,
            15300, 15093, 14882, 14668, 14452, 14235, 14015, 13795, 13573,
            13351, 13129, 12907, 12686, 12465, 12245, 12026, 11809, 11594,
            11380, 11168, 10958, 10751, 10545, 10341, 10140, 9940, 9742, 9546,
            9352, 9159, 8968, 8778, 8588, 8399, 8211, 8022, 7833, 7644, 7454,
            7263, 7071, 6877, 6681, 6483, 6283, 6080, 5875, 5666, 5454, 5239,
            5020, 4798, 4573, 4344, 4111, 3875, 3635, 3392, 3145, 2895, 2642,
            2387, 2128, 1868, 1605, 1340, 1074, 807, 538, 269, 0, -270, -539,
            -808, -1075, -1341, -1606, -1869, -2129, -2388, -2643, -2896,
            -3146, -3393, -3636, -3876, -4112, -4345, -4574, -4799, -5021,
            -5240, -5455, -5667, -5876, -6081, -6284, -6484, -6682, -6878,
            -7072, -7264, -7455, -7645, -7834, -8023, -8212, -8400, -8589,
            -8779, -8969, -9160, -9353, -9547, -9743, -9941, -10141, -10342,
            -10546, -10752, -10959, -11169, -11381, -11595, -11810, -12027,
            -12246, -12466, -12687, -12908, -13130, -13352, -13574, -13796,
            -14016, -14236, -14453, -14669, -14883, -15094, -15301, -15506,
            -15706, -15903, -16095, -16282, -16465, -16642, -16814, -16981,
            -17142, -17297, -17446, -17590, -17727, -17859, -17985, -18105,
            -18219, -18329, -18433, -18533, -18628, -18719, -18806, -18889,
            -18969, -19047, -19122, -19196, -19268, -19339, -19409, -19479,
            -19550, -19622, -19694, -19768, -19844, -19922, -20003, -20086,
            -20173, -20262, -20355, -20451, -20550, -20653, -20759, -20869,
            -20982, -21097, -21215, -21336, -21459, -21583, -21709, -21836,
            -21962, -22089, -22215, -22339, -22462, -22582, -22699, -22813,
            -22922, -23026, -23125, -23217, -23303, -23382, -23453, -23516,
            -23571, -23617, -23655, -23683, -23701, -23711, -23711, -23701,
            -23683, -23655, -23619, -23575, -23523, -23464, -23398, -23327,
            -23250, -23169, -23085, -22999, -22911, -22823, -22736, -22651,
            -22569, -22492, -22420, -22356, -22299, -22252, -22216, -22191,
            -22179, -22181, -22198, -22230, -22280, -22347, -22432, -22536,
            -22659, -22802, -22965, -23147, -23349, -23571, -23813, -24073,
            -24351, -24647, -24960, -25287, -25629, -25984, -26350, -26726,
            -27109, -27498, -27891, -28286, -28680, -29070, -29455, -29832,
            -30199, -30551, -30888, -31206, -31502, -31774, -32018, -32233,
            -32415, -32562, -32672, -32741, -32767, -32749, -32684, -32571,
            -32406, -32189, -31919, -31593, -31211, -30771, -30274, -29719,
            -29105, -28432, -27701, -26912, -26065, -25162, -24204, -23191,
            -22127, -21011, -19847, -18637, -17382, -16087, -14752, -13382,
            -11980, -10548, -9090, -7610, -6111, -4597, -3071, -1538};
}
